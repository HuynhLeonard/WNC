import axios from "axios";

export const login = async (username, password) => {
  const reponse = await axios.post("http://localhost:8080/auth/token", {
    username,
    password,
  });

  const accessToken = reponse.data.result.token;
  const refreshToken = reponse.data.result.refreshToken;

  localStorage.setItem("ACCESS_TOKEN", accessToken);
  localStorage.setItem("REFRESH_TOKEN", refreshToken);
};

export const logout = async () => {
  const refreshToken = localStorage.getItem("REFRESH_TOKEN");
  
  await axios.post("http://localhost:8080/auth/logout", {
    token: refreshToken,
  });
};
