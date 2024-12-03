import axios from "axios";

const accessToken = localStorage.getItem("ACCESS_TOKEN") ?? "";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:5173",
  headers: {
    "Content-type": "application/json",
    Authorization: `Bearer ${accessToken}`,
  },
});

export const getTasks = async () => {
  const response = await axiosInstance.get("http://localhost:8080/api/todo");

  return response.data;
};

export const setCompleted = async (task, completed) => {
  const response = await axiosInstance.patch(
    `http://localhost:8080/api/todo/${task.id}`,
    {
      ...task,
      completed: completed,
    }
  );

  return response.data;
};

export const addTask = async (task) => {
  const response = await axiosInstance.post(`http://localhost:8080/api/todo`, {
    ...task,
  });

  return response.data;
};
