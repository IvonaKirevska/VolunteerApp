import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");
    const username = localStorage.getItem("username");

    const isLoggedIn = !!token;

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        localStorage.removeItem("username");
        navigate("/login");
    };

    const handleEventsClick = () => {
        if (isLoggedIn) {
            navigate("/events");
        } else {
            navigate("/login");
        }
    };

    return (
        <nav className="navbar">
            <div className="logo" onClick={() => navigate("/")}>
                Volunteer Events System
            </div>



            <div className="nav-links">
                <Link to="/add-event">Add Event</Link>
                <button onClick={handleEventsClick} className="link-btn">
                    Events
                </button>

                {isLoggedIn ? (
                    <button onClick={logout} className="logout-btn">
                        Logout
                    </button>
                ) : (
                    <Link to="/login">Login</Link>
                )}

                {isLoggedIn && username && (
                    <div className="user-name">
                        👤 {username}
                    </div>
                )}

            </div>
        </nav>
    );
}
