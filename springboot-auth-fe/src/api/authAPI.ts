import axios, { AxiosResponse } from "axios";

export const LoginUser = async (email: string, password: string): Promise<AxiosResponse<any>> => {
  const url: string = `${import.meta.env.VITE_APP_API}/auth/login`;
  const data = { email, password };
  return await axios.post(url, data);
};

export const RegisterUser = async (
  firstname: string,
  lastname: string,
  email: string,
  password: string
): Promise<AxiosResponse<any>> => {
  const url: string = `${import.meta.env.VITE_APP_API}/auth/register`;
  const data = { firstname, lastname, email, password };
  return await axios.post(url, data);
};
