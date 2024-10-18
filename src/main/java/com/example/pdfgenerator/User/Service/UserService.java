package com.example.pdfgenerator.User.Service;

import com.example.pdfgenerator.User.DTO.RequestUserDTO;
import com.example.pdfgenerator.User.DTO.ResponseUserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import com.example.pdfgenerator.User.Repository.UserRepository;
import com.example.pdfgenerator.User.Service.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    public void save(RequestUserDTO dto){
        repo.save(mapper.toUser(dto));
    }

    public List<ResponseUserDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromUser).toList();
    }

    public ResponseUserDTO getById(Long id){
        return mapper.fromUser(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public ResponseUserDTO getByName(String name){
        Optional<Candidate> Candidate = repo.findByName(name);
        return mapper.fromUser(Candidate.orElseThrow());
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
