import FeedPostBlock from "@/components/FeedPostBlock";
import { mockFeedPosts } from "@/types/PostType";

export default function FeedPage() {
  const posts = mockFeedPosts;
  return (
    <div className='flex flex-col gap-10 overflow-y-auto'>
      {posts.map((post) => (
        <FeedPostBlock {...post} />
      ))}
    </div>
  );
}
