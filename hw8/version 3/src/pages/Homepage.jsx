import { useEffect } from "react";

import AddTask from "../components/AddTask";
import FilterTask from "../components/FilterTask";
import TaskList from "../components/TaskList";
import LoadingAnimation from "../components/LoadingAnimation";

import { useTaskContext } from "../useTaskContext";

import { getTasks } from "../api/todo";
import { logout } from "../api/auth";

export default function Homepage() {
  const { dispatch } = useTaskContext();

  useEffect(() => {
    async function getData() {
      dispatch({ type: "SET_LOADING", payload: true });

      const taskList = await getTasks();
      dispatch({ type: "SET_TASK_LIST", payload: taskList });

      dispatch({ type: "SET_LOADING", payload: false });
    }
    getData();
  }, [dispatch]);

  async function handleLogout() {
    await logout();
    localStorage.clear();
  }

  return (
    <div>
      <div className="flex justify-end mt-10">
        <button
          onClick={() => handleLogout()}
          className="mr-10 w-40 p-2 font-medium text-black bg-gray-300 hover:bg-gray-200 rounded-md"
        >
          Logout
        </button>
      </div>
      <div className="flex justify-center">
        <div className="inline-flex flex-col items-start">
          <AddTask />
          <FilterTask />
        </div>
      </div>

      <TaskList />

      <LoadingAnimation />
    </div>
  );
}
