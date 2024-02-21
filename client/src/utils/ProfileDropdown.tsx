import { Dropdown, DropdownTrigger, DropdownMenu, DropdownItem, Avatar } from "@nextui-org/react";

const ProfileDropdown = () => {
	return (
		<Dropdown placement="bottom-start" className="border border-1 border-secondary-200">
			<DropdownTrigger>
				<Avatar color="primary" className="w-10 h-10 my-auto mr-20" />
			</DropdownTrigger>

			<DropdownMenu aria-label="Profile Actions" variant="flat">
				<DropdownItem key="profile" className="h-14 gap-2">
					<p className="font-semibold">Signed in as</p>
					<p className="font-semibold">zoey@example.com</p>
				</DropdownItem>
				<DropdownItem key="settings">My Settings</DropdownItem>
				<DropdownItem key="team_settings">Team Settings</DropdownItem>
				<DropdownItem key="analytics">Analytics</DropdownItem>
				<DropdownItem key="system">System</DropdownItem>
				<DropdownItem key="configurations">Configurations</DropdownItem>
				<DropdownItem key="help_and_feedback">Help & Feedback</DropdownItem>
				<DropdownItem key="logout" color="danger">
					Log Out
				</DropdownItem>
			</DropdownMenu>
		</Dropdown>
	);
};

export default ProfileDropdown;
