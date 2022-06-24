export interface LoginResponse {
  accessToken: string;
  refreshToken: string;
  email: string;
  roles: string[];
}
