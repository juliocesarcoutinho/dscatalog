const Navbar = () => {
    return (
        <nav className="bg-primary navbar ">
            <div>
                <a href="link">
                    <h4>DS Catalog</h4>
                </a>

                <div>
                    <ul className="text-uppercase">
                        <li>
                            <a href="link">Home</a>
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