import './styles.css'
import 'bootstrap/js/src/collapse.js'

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-md bg-primary main-nav navbar-dark">
            <div className="container-fluid">
                <a href="/" className="nav-logo-text">
                    <h4>DS Catalog</h4>
                </a>

                <button className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#dscatalog-navbar"
                        aria-controls="dscatalog-navbar"
                        aria-expanded="false"
                        aria-label="Toogle Navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="dscatalog-navbar">
                    <ul className="text-uppercase navbar-nav offset-md-2 main-menu">
                        <li>
                            <a href="link" className="active">Home</a>
                        </li>
                        <li>
                            <a href="link">Catálogo</a>
                        </li>
                        <li>
                            <a href="link">Admin</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;