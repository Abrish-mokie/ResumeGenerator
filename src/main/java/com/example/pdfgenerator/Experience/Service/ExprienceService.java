package com.example.pdfgenerator.Experience.Service;


import com.example.pdfgenerator.Education.DTO.RequestEducationDTO;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Repository.EducationRepository;
import com.example.pdfgenerator.Education.Service.Mapper.EducationMapper;
import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Repository.ExperiencesRepository;
import com.example.pdfgenerator.Experience.Service.Mapper.ExprienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExprienceService {

    private final ExperiencesRepository repo;
    private final ExprienceMapper mapper;

    public void save(RequestExprienceDTO dto){
        repo.save(mapper.toExprience(dto));
    }

    public List<ResponseExprienceDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromExprience).toList();
    }

    public ResponseExprienceDTO getById(Long id){
        return mapper.fromExprience(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public void patch(Long id, Map<String,Object> values){
        var toBePatched = repo.getReferenceById(id);

        for(Map.Entry<String,Object> entry: values.entrySet()){
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            try{
                String setterMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Method setter = toBePatched.getClass().getMethod(setterMethodName, String.class);
                setter.invoke(toBePatched,fieldValue.toString());
            }
            catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                   InvocationTargetException e)
            {
                throw new RuntimeException("error patching");
            }
        }

        repo.save(toBePatched);
    }
}
