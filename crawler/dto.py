class DTO:
    def __init__(self):
        self.contest_no = None
        self.title = None
        self.view_count = None
        self.contest_field = []
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

    def __str__(self):
        return f'contest_no : {self.contest_no}'

    def set_contest_no(self, payload):
        self.contest_no = payload + 1
        self.img_src = f'co{self.contest_no}.jpg'