struct Solution;

impl Solution {
    pub fn find_min_arrow_shots(mut points: Vec<Vec<i32>>) -> i32 {
        if points.is_empty() {
            return 0;
        }

        // Sort points by ending coordinate
        points.sort_by(|a, b| a[1].cmp(&b[1]));
        let mut points_iter = points.iter();
        // Consume first iterator element to setup the first arrow
        let mut arrow_position = points_iter.next().unwrap()[1];
        // Iterate over the balloon and shoot the remaining ones at each round
        return points_iter.fold(1, |arrows_count, balloon| {
            return if balloon[0] > arrow_position {
                arrow_position = balloon[1];
                arrows_count + 1
            } else {
                arrows_count
            }
        });
    }
}