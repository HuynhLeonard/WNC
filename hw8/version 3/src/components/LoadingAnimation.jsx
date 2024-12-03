import { useTaskContext } from "../useTaskContext";
import Loading from "../assets/loading.svg";

export default function LoadingAnimation() {
  const { state } = useTaskContext();

  return (
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
  );
}
