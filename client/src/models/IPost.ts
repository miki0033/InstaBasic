interface IPost {
	id?: number;
	title: string;
	description: string;
	imageUrl: string[];
	likes?: number;
	type: "single" | "carousel" | "story";
	profile: number;
	createdAt?: string;
}
