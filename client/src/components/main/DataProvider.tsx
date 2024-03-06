import { Dispatch, ReactNode, createContext, useContext, useReducer } from "react";

//
const InitContext = {
	user: undefined,
	profile: undefined,
	follower: [],
	followed: [],
	posts: [],
};
const test_login_Action: IState = {
	user: {
		id: 1,
		userName: "Seris_Dantalian",
		email: "seris.dantalian@gmail.com",
	},
	profile: {
		id: 1,
		userName: "Seris_Dantalian",
		firstName: "Seris",
		lastName: "Dantalian",
		birthday: "21-08-2001",
		bio: "Hi! I'm Seris, here to have fun!",
		avatarUrl: "http://localhost:4000/pfp/get/1.jpg",
		userId: 1,
	},
	follower: [],
	followed: [],
	posts: [
		{
			title: "Purple",
			description: "My purple themed background images",
			url: ["http://localhost:4000/post/get/1_1.jpg", "http://localhost:4000/post/get/1_2.png"],
			likes: 15,
			type: "carousel",
			profileId: 1,
			createdAt: "2024-02-25",
		},
		{
			title: "Ace",
			description: "Asexual flag's hex-codes",
			url: ["http://localhost:4000/post/get/1_3.png"],
			likes: 20,
			type: "single",
			profileId: 1,
			createdAt: "2024-02-29",
		},
	],
};

// context type
interface IState {
	user: IUser | undefined;
	profile: IProfile | undefined;
	follower: IFollow[];
	followed: IFollow[];
	posts: IPost[];
}
interface IDataContext {
	state: IState;
	dispatch: Dispatch<ACTIONTYPE>;
}

// reducer actions custom type
type ACTIONTYPE =
	| {
			type: "LOG_IN";
			payload: { userName: string; password: string };
	  }
	| {
			type: "LOG_OUT";
	  }
	| {
			type: "REGISTER";
			payload: {
				firstName: string;
				lastName: string;
				userName: string;
				email: string;
				confirmEmail: string;
				password: string;
				confirmPassword: string;
				birthday: string;
			};
	  };

// context reducer - gestisco le varie actions possibili
const reducer = (state: IDataContext["state"], action: ACTIONTYPE) => {
	switch (action.type) {
		case "LOG_IN":
			return test_login_Action;
		case "LOG_OUT":
			return InitContext;
		case "REGISTER":
			return InitContext;
		default:
			return state;
	}
};

// create context
const DataContext = createContext<IDataContext | null>(null);

// context hook
const useData = () => {
	const dataContext = useContext(DataContext);
	if (!dataContext) {
		throw new Error("useStore cannot be used outside of <StoreContext.Provider>");
	}
	return dataContext;
};

// context provider wrapper component - inizializzo il valore del context fornendo accesso al reducer
const DataProvider = ({ children }: { children: ReactNode }) => {
	// gestiamo lo state del context con l'hook useReducer
	const [state, dispatch] = useReducer(reducer, InitContext);

	return <DataContext.Provider value={{ state, dispatch }}>{children}</DataContext.Provider>;
};

export { useData, DataProvider };
