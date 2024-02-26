import { Routes, Route, Navigate } from "react-router-dom";

import Layout from "../layout/Layout";

import PageNotFound from "../pages/PageNotFound";
import { useData } from "./DataProvider";
import { Login } from "../pages/login/Login";
import Register from "../pages/login/Register";

function App() {
	const {
		state: { profile },
	} = useData();

	return (
		<>
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<Navigate to={profile ? "/home" : "/login"} />} />

					<Route path="/home">
						<Route index element={<>Homepage</>} />
					</Route>

					<Route path="/profile">
						<Route index element={<>Profile</>} />

						<Route path="settings">
							<Route index element={<>Profile Settings</>} />
						</Route>
					</Route>

					<Route path="/login">
						<Route index element={<Login />} />

						<Route path="register">
							<Route index element={<Register />} />
						</Route>
					</Route>
				</Route>

				<Route path="*" element={<PageNotFound />} />
			</Routes>
		</>
	);
}

export default App;
