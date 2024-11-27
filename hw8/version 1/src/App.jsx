import { useState } from "react";
import TaskList from "./TaskList";
import AddTask from "./AddTask";
import FilterTask from "./FilterTask";

function App() {
  const [tasks, setTasks] = useState([]);
  const [taskName, setTaskName] = useState("");
  const [filter, setFilter] = useState("");

  const handleAddTask = () => {
    if (taskName.trim() !== "") {
      setTasks([...tasks, { id: Date.now(), name: taskName }]);
      setTaskName("");
    }
  };

  const filteredTasks = tasks.filter((task) =>
    task.name.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <div>
      <div className="flex justify-center">
        <div className="inline-flex flex-col items-start">
          <AddTask
            taskName={taskName}
            setTaskName={setTaskName}
            handleAddTask={handleAddTask}
          />
          <FilterTask filter={filter} setFilter={setFilter} />
        </div>
      </div>

      <TaskList tasks={filteredTasks} />
    </div>
  );
}

export default App;
