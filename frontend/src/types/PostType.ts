export type FeedPost = {
  profileUrl: string;
  userEmail: string;
  postImageUrl: string;
  likeCount: number;
  commentCount: number;
  text: string;
  createdAt: Date;
  workingOutToday: string | null;
};

export const mockFeedPosts: FeedPost[] = [
  {
    profileUrl: "https://avatars.githubusercontent.com/u/48677366?v=4",
    userEmail: "rkdtmddnjs97@gmail.com",
    postImageUrl:
      "https://cdnimage.dailian.co.kr/news/202401/news_1704518501_1314695_m_1.jpeg",
    likeCount: 20,
    commentCount: 5,
    text: "와 뉴진스",
    createdAt: new Date("2024-11-26 00:00:00"),
    workingOutToday: null,
  },
  {
    profileUrl: "https://avatars.githubusercontent.com/u/48677366?v=4",
    userEmail: "rkdtmddnjs97@gmail.com",
    postImageUrl:
      "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/201706/07/bbcf94e4-b533-4a5e-82c8-b7bc576cd8a0.jpg",
    likeCount: 20,
    commentCount: 5,
    text: "와 오늘 운동 미쳤다잉",
    createdAt: new Date("2024-12-23 12:00:00"),
    workingOutToday: null,
  },
  {
    profileUrl: "https://avatars.githubusercontent.com/u/48677366?v=4",
    userEmail: "rkdtmddnjs97@gmail.com",
    postImageUrl:
      "https://www.starinnews.com/news/photo/202204/335565_335012_1843.jpg",
    likeCount: 20,
    commentCount: 5,
    text: "와 오늘 운동 미쳤다잉",
    createdAt: new Date("2024-12-25 12:00:00"),
    workingOutToday: null,
  },
  {
    profileUrl: "https://avatars.githubusercontent.com/u/48677366?v=4",
    userEmail: "rkdtmddnjs97@gmail.com",
    postImageUrl:
      "https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/c0Om/image/3-rCttBycNDXBsDzIcnlq-jaF1U.jpg",
    likeCount: 20,
    commentCount: 5,
    text: "와 오늘 운동 미쳤다잉",
    createdAt: new Date("2024-12-26 10:00:00"),
    workingOutToday: null,
  },
];
