# dto 어떻게 쓰더라...

class DTO:
    def __init__(self):
        self.contest_no = None
        self.title = None
        self.view_count = None
        self.contest_field = None
        self.target = None
        self.host = None
        self.sponsor = None
        self.start_date = None
        self.end_date = None
        self.total_reward = None
        self.first_reward = None
        self.homepage = None
        self.content = None
        self.img_src = None


if __name__ == '__main__':
    dto = DTO()

    print(dir(dto))