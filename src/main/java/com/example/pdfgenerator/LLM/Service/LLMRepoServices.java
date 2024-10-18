package com.example.pdfgenerator.LLM.Service;

import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.LLM.DTO.RequestLLMDTO;
import com.example.pdfgenerator.LLM.DTO.ResponseLLMDTO;
import com.example.pdfgenerator.LLM.Repository.LLMRepository;
import com.example.pdfgenerator.LLM.Service.Mapper.LLMMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LLMRepoServices {

    private final LLMRepository repo;
    private final LLMMapper mapper;

    public void save(String dto){
        repo.save(mapper.toPromptTemplate(dto));
    }

    public List<ResponseLLMDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromPromptTemplate).toList();
    }

    public ResponseLLMDTO getById(Long id){
        return mapper.fromPromptTemplate(repo.getReferenceById(id));
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
