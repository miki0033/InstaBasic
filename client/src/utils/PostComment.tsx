import { Avatar } from "@nextui-org/react";

const PostComment = ({ avatar, userName, text }: { avatar: string; userName: string; text: string }) => {
	return (
		<div className="flex flex-row gap-1">
			<div className="w-auto flex flex-row gap-1">
				<Avatar isBordered src={avatar} className="w-6 h-6" />
				<p className="text-primary-500">{(userName ? userName : "Unknown_User") + ":"}</p>
			</div>
			<div className="text-default-800">{text}</div>
		</div>
	);
};

export default PostComment;
