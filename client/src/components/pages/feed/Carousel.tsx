import { Avatar, Button } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";

const Carousel = () => {
	return (
		<div className="w-full h-full">
			<div className="w-full h-full bg-red-500 flex flex-row gap-5 px-10 overflow-auto scrollbar-hide">
				<Button isIconOnly className="w-16 h-16 my-auto rounded-full flex-none" color="success">
					<PlusCircleIcon />
				</Button>
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />;
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
			</div>
		</div>
	);
};

export default Carousel;
