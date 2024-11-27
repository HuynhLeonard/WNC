import TaskList from "./TaskList";
import AddTask from "./AddTask";
import FilterTask from "./FilterTask";

function App() {
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

export default App;
