import { useReducer } from "react";
import { TaskContext } from "./TaskContext";

const initialState = {
  tasks: [],
  taskName: "",
  filter: "",
  isLoading: false,
};

function taskReducer(state, action) {
  switch (action.type) {
    case "SET_TASK_LIST":
      return { ...state, tasks: action.payload };
    case "SET_LOADING":
      return { ...state, isLoading: action.payload };
    case "SET_TASK_NAME":
      return { ...state, taskName: action.payload };
    case "SET_FILTER":
      return { ...state, filter: action.payload };
    default:
      throw new Error(`Unhandled action type: ${action.type}`);
  }
}

export function TaskProvider({ children }) {
  const [state, dispatch] = useReducer(taskReducer, initialState);

  return (
    <TaskContext.Provider value={{ state, dispatch }}>
      {children}
    </TaskContext.Provider>
  );
}
