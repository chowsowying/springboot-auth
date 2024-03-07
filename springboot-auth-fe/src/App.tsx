import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useAppDispatch, useAppSelector } from "@/redux/store";
import { setUser } from "@/redux/userSlice";

// Pages
import Home from "@/pages/Home";
import Login from "@/pages/Login";
import Register from "@/pages/Register";
import AdminDashboard from "@/pages/admin/AdminDashboard";
import UserDashboard from "@/pages/user/UserDashboard";

//Component
import Loading from "@/components/Loading";
import AdminRoute from "@/components/routes/AdminRoute";
import UserRoute from "@/components/routes/UserRoute";

function App() {
  // Redux
  const dispatch = useAppDispatch();
  const { loading } = useAppSelector((state) => state.loaders);

  // Persist user data in redux
  useEffect(() => {
    const storedUserData = localStorage.getItem("user");
    if (storedUserData) {
      const user = JSON.parse(storedUserData);
      dispatch(setUser(user));
    }
  }, [dispatch]);

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
          <Route element={<AdminRoute />}>
            <Route path="/admin/dashboard" element={<AdminDashboard />} />
          </Route>
          {/* User Protected Route */}
          <Route element={<UserRoute />}>
            <Route path="/user/dashboard" element={<UserDashboard />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
