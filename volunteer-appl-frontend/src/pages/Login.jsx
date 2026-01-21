import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../axios/axios.js";
import "./Login.css";

export default function Login() {
    const [form, setForm] = useState({});
    const navigate = useNavigate();
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await api.post("/user/login", form);
            localStorage.setItem("token", res.data.token);
            localStorage.setItem("username", form.username);
            navigate("/events");
        } catch (err) {
            setError("Login failed. Check your credentials.");
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <h2>VolunteerApp Login</h2>
                {error && <p className="error">{error}</p>}
                <input
                    placeholder="Username"
                    name="username"
                    onChange={(e) => setForm({ ...form, [e.target.name]: e.target.value })}
                />
                <input
                    placeholder="Password"
                    type="password"
                    name="password"
                    onChange={(e) => setForm({ ...form, [e.target.name]: e.target.value })}
                />
                <button type="submit">Login</button>
                <p className="signup-text">
                    Don’t have an account? <a href="/register">Register here</a>
                </p>
            </form>
        </div>
    );
}
