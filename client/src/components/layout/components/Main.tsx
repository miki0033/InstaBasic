import { ReactElement } from "react";

const Main = ({ children }: { children: ReactElement }) => {
	return (
		<div className="h-[90vh] py-3 px-5">
			<div className="h-full">{children}</div>
		</div>
	);
};

export default Main;
