type Props = {
  bgColor: string;
  logoUrl: string;
  text: string;
};

export default function SocialLoginButton({ bgColor, logoUrl, text }: Props) {
  return (
    <button className={`flex ${bgColor} rounded-md py-4 px-6 gap-5`}>
      <img src={logoUrl} />
      <p className='text-black font-bold'>{text}</p>
    </button>
  );
}
