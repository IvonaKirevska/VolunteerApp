import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import api from "../axios/axios";
import "./Register.css";

export default function Register() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const [error, setError] = useState("");

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            setError("Лозинките не се совпаѓаат");
            return;
        }

        try {
            await api.post("/user/register", {
                username: formData.username,
                password: formData.password,
                email: formData.email
            });

            navigate("/login");
        } catch (err) {
            setError("Регистрацијата не успеа");
        }
    };

    return (
        <div className="register-container">
            <h2>Регистрација</h2>

            {error && <p className="error">{error}</p>}

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="username"
                    placeholder="Корисничко име"
                    value={formData.username}
                    onChange={handleChange}
                    required
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Лозинка"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />

                <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Потврди лозинка"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                />

                <button type="submit">Register</button>
            </form>

            <p>
                Веќе имаш акаунт? <Link to="/login">Login</Link>
            </p>
        </div>
    );
}
