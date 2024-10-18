package com.example.pdfgenerator.Education.Service;


import com.example.pdfgenerator.Education.DTO.RequestEducationDTO;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Model.Education;
import com.example.pdfgenerator.Education.Repository.EducationRepository;
import com.example.pdfgenerator.Education.Service.Mapper.EducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository repo;
    private final EducationMapper mapper;

    public void save(RequestEducationDTO dto){
        repo.save(mapper.toEducation(dto));
    }

    public List<ResponseEducationDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromEducation).toList();
    }

    public ResponseEducationDTO getById(Long id){
        return mapper.fromEducation(repo.getReferenceById(id));
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
