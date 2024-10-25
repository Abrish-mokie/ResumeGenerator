package com.example.pdfgenerator.Projects.Service;

import com.example.pdfgenerator.Projects.DTO.RequestProjectsDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Model.Projects;
import com.example.pdfgenerator.Projects.Repository.ProjectsRepository;
import com.example.pdfgenerator.Projects.Service.Mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServices {

    private final ProjectsRepository repo;
    private final ProjectMapper mapper;

    public void save(RequestProjectsDTO dto){
        repo.save(mapper.toProjects(dto));
    }

    public List<ResponseProjectDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromProjects).toList();
    }

    public ResponseProjectDTO getById(Long id){
        return mapper.fromProjects(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public void patch(Long id, Map<String,Object> values){
        var toBePatched = repo.getReferenceById(id);

        for(Map.Entry<String,Object> entry: values.entrySet()){
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            System.out.println("hello");
            log.info("patching project {}",fieldName);
            log.info("type of projectObjectives is {}",fieldValue.getClass());
            if(Objects.equals(fieldName, "projectObjectives")){
                try {
                    String setterMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                    Method setter = toBePatched.getClass().getMethod(setterMethodName, List.class);
                    setter.invoke(toBePatched, fieldValue);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                         InvocationTargetException e) {
                    throw new RuntimeException("error patching list");
                }
            }else {
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
        }

        repo.save(toBePatched);
    }

}
