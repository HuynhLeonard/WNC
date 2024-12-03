import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
} from "react-router-dom";
import { TaskProvider } from "./TaskProvider.jsx";
import Homepage from "./pages/Homepage.jsx";
import Loginpage from "./pages/Loginpage.jsx";
import "./index.css";

const isAuthenticated = localStorage.getItem("ACCESS_TOKEN");

const router = createBrowserRouter([
  {
    path: "/",
    element: isAuthenticated ? <Homepage /> : <Navigate to="/login" />,
    index: true,
  },
  {
    path: "/login",
    element: <Loginpage />,
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <TaskProvider>
      <RouterProvider router={router} />
    </TaskProvider>
  </StrictMode>
);
