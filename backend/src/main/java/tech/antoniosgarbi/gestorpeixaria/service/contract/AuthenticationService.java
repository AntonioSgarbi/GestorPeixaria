package tech.antoniosgarbi.gestorpeixaria.service.contract;

import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginRequest;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.LoginResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.auth.RefreshResponse;
import tech.antoniosgarbi.gestorpeixaria.model.Funcionario;
import tech.antoniosgarbi.gestorpeixaria.model.User;

public interface AuthenticationService {
    LoginResponse authenticateUser(LoginRequest loginRequest);

    RefreshResponse refreshTheToken(String requestRefreshToken);

    User criarUsuariodeFuncionario(Funcionario funcionario);

    //public test version
    void resetPassword(String email);
}
