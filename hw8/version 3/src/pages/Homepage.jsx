import AddTask from "../components/AddTask";
import FilterTask from "../components/FilterTask";
import TaskList from "../components/TaskList";

export default function Homepage() {
  return (
    <div>
      <div className="flex justify-center">
        <div className="inline-flex flex-col items-start">
          <AddTask />
          <FilterTask />
        </div>
      </div>

      <TaskList />
    </div>
  );
}
