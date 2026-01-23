import "./Footer.css";

export default function Footer() {
    return (
        <footer className="footer">
            <p>© {new Date().getFullYear()} Volunteer-Event System</p>
            <p>Made with ❤️ for the community</p>
        </footer>
    );
}