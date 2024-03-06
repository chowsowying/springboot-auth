import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useAppDispatch, useAppSelector } from "@/redux/store";

// Pages
import Home from "@/pages/Home";
import Login from "@/pages/Login";
import Register from "@/pages/Register";
import AdminDashboard from "@/pages/admin/AdminDashboard";
import UserDashboard from "@/pages/user/UserDashboard";

//Component
import Loading from "@/components/Loading";

function App() {
  // Redux
  const dispatch = useAppDispatch();
  const { loading } = useAppSelector((state) => state.loaders);
  return (
    <>
      {loading && <Loading />}
      <ToastContainer />
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          {/* Admin Protected Route */}
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          {/* User Protected Route */}
          <Route path="/user/dashboard" element={<UserDashboard />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
