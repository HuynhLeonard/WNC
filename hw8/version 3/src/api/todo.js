import axios from "axios";

export const getTasks = async () => {
  const response = await axios.get("http://localhost:8080/api/todo");

  return response.data;
};

export const setCompleted = async (task, completed) => {
  const response = await axios.patch(
    `http://localhost:8080/api/todo/${task.id}`,
    {
      ...task,
      completed: completed,
    }
  );

  return response.data;
};

export const addTask = async (task) => {
  const response = await axios.post(`http://localhost:8080/api/todo`, {
    ...task,
  });

  return response.data;
};
