interface IPost {
	id?: number;
	title: string;
	description: string;
	url: string;
	likes: number;
	type: "single" | "carousel" | "story";
	profileId: number;
	createdAt: string;
}
