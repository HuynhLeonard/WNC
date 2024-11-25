import { useReducer } from "react";
import { TaskContext } from "./TaskContext";

const initialState = {
  tasks: [],
  taskName: "",
  filter: "",
};

function taskReducer(state, action) {
  switch (action.type) {
    case "SET_TASK_NAME":
      return { ...state, taskName: action.payload };
    case "ADD_TASK":
      return {
        ...state,
        tasks: [...state.tasks, { id: Date.now(), name: state.taskName }],
        taskName: "",
      };
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
