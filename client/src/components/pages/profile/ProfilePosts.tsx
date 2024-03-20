import axios from "axios";
import { useData } from "../../main/DataProvider";
import AddPostCard from "./cards/AddPostCard";
import PostCard from "./cards/PostCard";
import { useState } from "react";

export const ProfilePosts = () => {
	const {
		state: {
			profile: { profilename },
			token,
			type,
		},
	} = useData();

	const GET_POSTS = import.meta.env.VITE_GET_POSTS_BY_PROFILE_NAME;
	const [profilePosts, uploadPosts] = useState<IPost[]>([]);

	const getPosts = async () => {
		const config = {
			headers: { Authorization: type + " " + token },
		};
		const posts = await axios.get(GET_POSTS + profilename, config).then((res) => {
			return res.data.content;
		});
		return posts;
	};
	const reloadImgs = async () => {
		uploadPosts(await getPosts());
	};
	reloadImgs();

	return (
		<div className="w-full h-full px-14 pt-10 pb-5 flex flex-row flex-wrap justify-between gap-10">
			<AddPostCard signal={reloadImgs} key={0} />
			{profilePosts.map((el, index) => {
				return (
					<>
						<PostCard post={el} key={el?.profile + index} />
					</>
				);
			})}
		</div>
	);
};
