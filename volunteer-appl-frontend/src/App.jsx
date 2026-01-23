import {useState} from "react";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import TestConnection from "./pages/TestConnection"; // create this file
import reactLogo from "./assets/react.svg";
import "./App.css";
import Login from "./pages/Login.jsx";
import Events from "./pages/Events.jsx";
import Navbar from "./components/Navbar.jsx";
import Register from "./pages/Register.jsx";
import AddEvent from "./pages/AddEvent.jsx";
import EventDetails from "./pages/EventDetails.jsx";
import PrivateRoute from "./PrivateRoute.jsx";
import EditEvent from "./pages/EditEvent.jsx";
import Home from "./pages/Home.jsx";
import Layout from "./components/Layout.jsx";

function App() {
    return (
        <Router>
            <Navbar />
                <Routes>
                    <Route element={<Layout/>}>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register />} />
                    <Route path="/test" element={<TestConnection/>}/>
                    <Route
                        path="/"
                        element={<Home/>}
                    />
                    <Route path="/events" element={<PrivateRoute><Events/></PrivateRoute>}/>
                    <Route path="/add-event" element={<PrivateRoute><AddEvent/></PrivateRoute>}/>
                    <Route path="/events/:id" element={<EventDetails />} />
                    <Route path="/events/:id/edit" element={<EditEvent />} />
                </Route>
                </Routes>
        </Router>
    );
}

export default App;
