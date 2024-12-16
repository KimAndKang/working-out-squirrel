/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'sq-dark': '#1F1C19',
        'sq-green': '#F9D790',
        'sq-brown': '#9C7454',
      },
    },
  },
  plugins: [],
};
