import { useEffect } from "react";

import AddTask from "../components/AddTask";
import FilterTask from "../components/FilterTask";
import TaskList from "../components/TaskList";
import { useTaskContext } from "../useTaskContext";
import { getTasks } from "../api/todo";
import Loading from "../assets/loading.svg";

export default function Homepage() {
  const { state, dispatch } = useTaskContext();

  useEffect(() => {
    async function getData() {
      dispatch({ type: "SET_LOADING", payload: true });

      const taskList = await getTasks();
      dispatch({ type: "SET_TASK_LIST", payload: taskList });

      dispatch({ type: "SET_LOADING", payload: false });
    }
    getData();
  }, [dispatch]);

  return (
    <div>
      <div className="flex justify-center">
        <div className="inline-flex flex-col items-start">
          <AddTask />
          <FilterTask />
        </div>
      </div>

      <TaskList />

      <div
        className={`fixed top-0 bottom-0 left-0 right-0 z-10 flex items-center justify-center bg-white bg-opacity-30 ${
          state.isLoading ? "block" : "hidden"
        }`}
      >
        <div className="w-[200px] h-[100px] bg-white rounded-md border shadow-md flex flex-col items-center justify-center">
          <img src={Loading} alt="loading" className="w-10 h-10 animate-spin" />
          <h4>Loading</h4>
        </div>
      </div>
    </div>
  );
}
