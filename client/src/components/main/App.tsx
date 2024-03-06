import { Routes, Route, Navigate } from "react-router-dom";

import Layout from "../layout/Layout";
import PageNotFound from "../pages/PageNotFound";
import { useData } from "./DataProvider";
import { Login } from "../pages/login/Login";
import Register from "../pages/login/Register";
import Feed from "../pages/feed/Feed";
import Profile from "../pages/profile/Profile";
import Settings from "../pages/settings/Settings";

function App() {
	const {
		state: { profile },
	} = useData();

	return (
		<>
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<Navigate to={!profile ? "/login" : "/home"} />} />

					<Route path="/home">
						<Route index element={<Feed />} />
					</Route>

					<Route path="/profile">
						<Route index element={<Profile />} />

						<Route path="settings">
							<Route index element={<Settings />} />
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
