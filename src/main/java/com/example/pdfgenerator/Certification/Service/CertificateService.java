package com.example.pdfgenerator.Certification.Service;

import com.example.pdfgenerator.Certification.DTO.RequestCertificateDTO;
import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Certification.Repository.CertificatesRepository;
import com.example.pdfgenerator.Certification.Service.Mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificatesRepository repo;
    private final CertificateMapper mapper;

    public void save(RequestCertificateDTO dto){
        repo.save(mapper.toCertificate(dto));
    }

    public List<ResponseCertificateDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromCertificate).toList();
    }

    public ResponseCertificateDTO getById(Long id){
        return mapper.fromCertificate(repo.getReferenceById(id));
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
