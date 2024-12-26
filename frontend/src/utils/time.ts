export function getTimeElapsed(date: Date): string {
  const now = new Date();
  const diff = now.getTime() - date.getTime(); //ms딘위

  const miniute = 60 * 1000;

  const units = [
    { label: "분", value: 60 * miniute },
    { label: "시간", value: 24 * 60 * miniute },
    { label: "일", value: 30 * 24 * 60 * miniute },
    { label: "달", value: 365 * 30 * 24 * 60 * miniute },
    { label: "년", value: 365 * 30 * 24 * 60 * miniute },
  ];

  let previousValue = 1000;
  for (const unit of units) {
    if (diff < unit.value || unit.label == "년") {
      return `${Math.floor(diff / previousValue)}${unit.label} 전`;
    }
    previousValue = unit.value;
  }
  return "방금 전";
}
