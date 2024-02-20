import Footer from "./components/Footer";
import Header from "./components/Header";
import Main from "./components/Main";

import { Outlet } from "react-router-dom";

const Layout = () => {
	return (
		<div className="h-[100vh] bg-default-50 text-center text-default-600">
			<Header />
			<Main children={<Outlet />} />
			<Footer />
		</div>
	);
};

export default Layout;
