import { Routes, Route } from "react-router-dom";

import Layout from "../layout/Layout";

import PageNotFound from "../../utils/PageNotFound";

function App() {
	return (
		<>
			{/* prettier-ignore */}
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<>Home</>} />

					<Route path="feed">
						<Route index element={<>Feed</>} />
					</Route>

					<Route path="profile">
						<Route index element={<>Profile</>}/>

						<Route path="settings">
							<Route index element={<>Settings</>}/>
						</Route>
					</Route>

					
				</Route>

				<Route path="*" element={<PageNotFound/>} />
			</Routes>
		</>
	);
}

export default App;
