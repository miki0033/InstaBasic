import Carousel from "./Carousel";

const Feed = () => {
	return (
		<div className=" w-full h-full flex flex-row">
			<div className="w-1/5 bg-content2 border-2 border-secondary-400 border-r-divider rounded-l-md"></div>
			<div className="w-3/5 bg-content2 border-2 border-primary-400 border-x-divider">
				<div className="w-full h-32 ">
					<Carousel />
				</div>
			</div>
			<div className="w-1/5 bg-content2 border-2 border-secondary-400 border-l-divider rounded-r-md"></div>
		</div>
	);
};

export default Feed;
