import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import "./Layout.css";

export default function Layout() {
    return (
        <div className="page">
            <main className="content">
                <Outlet />
            </main>
            <Footer />
        </div>
    );
}