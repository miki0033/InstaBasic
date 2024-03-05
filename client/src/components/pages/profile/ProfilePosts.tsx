import { useData } from "../../main/DataProvider";
import AddPostCard from "./AddPostCard";
import PostCard from "./PostCard";

export const ProfilePosts = () => {
	const {
		state: { posts },
	} = useData();

	return (
		<div className="w-full h-full px-14 pt-10 pb-5 flex flex-row flex-wrap justify-between gap-10">
			<AddPostCard />
			{posts.map((el) => {
				return (
					<>
						<PostCard post={el} />
					</>
				);
			})}
		</div>
	);
};
