package proyecto.vetefaas.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import proyecto.vetefaas.dtos.UsuarioDto;

@Service
public class UsuarioService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String CREAR_USUARIO_FUNCTION_URL = "https://crearusuario.azurewebsites.net/api/crearusuario";
    private final String ASIGNAR_ROL_FUNCTION_URL = "https://crearusuario.azurewebsites.net/api/asignarrol";

    public String crearUsuario(UsuarioDto usuarioDto) {
        String requestBody = usuarioDto.getNombre() + "," + usuarioDto.getEmail();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                CREAR_USUARIO_FUNCTION_URL,
                HttpMethod.POST,
                requestEntity,
                String.class);

        return response.getBody();
    }

    public String asignarRol(String userId, String rolId) {
        String urlConParams = ASIGNAR_ROL_FUNCTION_URL + "?userId=" + userId + "&rolId=" + rolId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                urlConParams,
                HttpMethod.POST,
                requestEntity,
                String.class);

        return response.getBody();
    }
}
