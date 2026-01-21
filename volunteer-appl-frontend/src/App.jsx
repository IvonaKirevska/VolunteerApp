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

function App() {
    return (
        <Router>
            <Navbar />
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register />} />
                    <Route path="/test" element={<TestConnection/>}/>
                    <Route
                        path="/"
                        element={<h1>Welcome to VolunteerApp Frontend</h1>}
                    />
                    <Route path="/events" element={<Events/>}/>
                    <Route path="/add-event" element={<AddEvent/>}/>
                    <Route path="/events/:id" element={<EventDetails />} />
                </Routes>
        </Router>
    );
}

export default App;
