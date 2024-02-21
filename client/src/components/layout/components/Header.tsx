import ProfileDropdown from "../../../utils/ProfileDropdown";
import { ThemeSwitcher } from "../../../utils/ThemeSwitcher";

const Header = () => {
	return (
		<div className="h-[6vh] flex flex-row rounded-b-2xl bg-secondary-200">
			<div className="w-1/3 flex justify-left">
				<div className="my-auto ml-20">
					<ThemeSwitcher />
				</div>
			</div>
			<div className="w-1/3 flex flex-row">
				<div className="w-auto mx-auto flex flex-row ">
					<img src="/logo.png" className="w-9 h-9 my-auto" alt="Instabasic Logo" />
					<h1 className="w-[15rem] mx-auto my-auto text-4xl font-mono">Instabasic</h1>
				</div>
			</div>
			<div className="w-1/3 flex justify-end ">
				<ProfileDropdown />
			</div>
		</div>
	);
};

export default Header;
