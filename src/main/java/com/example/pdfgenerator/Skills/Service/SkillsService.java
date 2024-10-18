package com.example.pdfgenerator.Skills.Service;

import com.example.pdfgenerator.Skills.DTO.RequestSkillsDTO;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;
import com.example.pdfgenerator.Skills.Repository.SkillsRepository;
import com.example.pdfgenerator.Skills.Service.Mapper.SkillsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository repo;
    private final SkillsMapper mapper;

    public void save(RequestSkillsDTO dto){
        repo.save(mapper.toSkills(dto));
    }

    public List<ResponseSkillsDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromSkills).toList();
    }

    public ResponseSkillsDTO getById(Long id){
        return mapper.fromSkills(repo.getReferenceById(id));
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
